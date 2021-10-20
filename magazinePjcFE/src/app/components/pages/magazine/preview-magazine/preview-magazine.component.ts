import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {
  Magazine,
  MagazineComment,
  MagazineCommentMessage,
  MagazineLike,
} from 'src/app/modules/MagazineMessage.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-preview-magazine',
  templateUrl: './preview-magazine.component.html',
  styleUrls: ['./preview-magazine.component.css'],
})
export class PreviewMagazineComponent implements OnInit {
  public _activeMag: Magazine;
  public _newComment: MagazineComment;
  public _comments: Array<MagazineComment>;
  public _allowLikesMsg: string;
  public _allowCommentMsg: string;
  public _likeDate: string = '';
  public _alertMsg: string;
  public _successMsg: string;
  public _likesCounter: number;
  public _showAlertMsg: boolean = false;
  public _showSUccessMsg: boolean = false;
  // INIFINITE SCROLL
  public _actualPage: number;
  public _offset: number;
  public _limit: number = 20;
  public _finishPage: number = 5;
  public _showScrollHeight: number = 400;
  public _hideScrollHeight: number = 200;
  public _showGoUpButton: boolean;

  constructor(
    private _magazineService: MagazineService,
    private _route: ActivatedRoute,
    private _storageService: LocalStorageService
  ) {
    this._comments = new Array<MagazineComment>();
    this._activeMag = this.newEmptyMag();
    this._offset = 0;
    this._actualPage = 1;
    this._newComment = new MagazineComment(0, '', '', '', '');
  }

  ngOnInit(): void {
    this.generateMagazine();
    this.getCommets();
    this.getLikesCounter();
  }

  public newEmptyMag(): Magazine {
    return new Magazine(
      '',
      0,
      0,
      0,
      '0000-00-00',
      '',
      false,
      false,
      '',
      '',
      false,
      []
    );
  }

  private getCommets(): void {
    this._magazineService
      .getComments(
        `${this._route.snapshot.paramMap.get('name')}`,
        this._limit,
        this._offset
      )
      .subscribe((_success: MagazineComment[]) => {
        for (const _com of _success) {
          this._comments.push(_com);
        }
        this._offset += +this._limit;
      });
  }

  private setMessages(): void {
    this._allowCommentMsg =
      this._activeMag.allowComment === true ? 'recibe' : 'no recibe';
    this._allowLikesMsg =
      this._activeMag.allowLikes === true ? 'recibe' : 'no recibe';
  }

  private showAlertMsg(_message: string) {
    this._alertMsg = _message;
    this._showAlertMsg = true;
  }

  private generateMagazine() {
    this._magazineService
      .getMagazine(`${this._route.snapshot.paramMap.get('name')}`)
      .subscribe(
        (_success: Magazine[]) => {
          this._activeMag = _success[0];
          this.setMessages();
        },
        (_error: Error) => {
          console.log(_error);
        }
      );
  }

  private getLikesCounter() {
    this._magazineService
      .getLikesCounter(`${this._route.snapshot.paramMap.get('name')}`)
      .subscribe((_success: number) => {
        this._likesCounter = _success;
      });
  }

  public leaveLike() {
    if (this._likeDate.trim() != '') {
      this._magazineService
        .leaveLike(
          new MagazineLike(
            this._likeDate,
            this._activeMag.name,
            JSON.parse(`${this._storageService.getData('user')}`).email
          )
        )
        .subscribe(
          (_scs: string) => {
            if (_scs === 'NO_ERROR') {
              this.showSuccess('Se ha registrado');
              this.getLikesCounter();
            } else {
              this.showAlertMsg('No se ha podido registrar la reaccion');
            }
          },
          (_error: Error) => {
            console.log('Error');
            console.log(_error);
          }
        );
    } else {
      this.showAlertMsg('no has ingresado una fecha');
    }
    // let like: MagazineLike
    // TODO leave likes
  }

  public commentMag() {
    if (this.formValid()) {
      this._newComment = new MagazineComment(
        0,
        this._newComment.dateString,
        this._newComment.text,
        JSON.parse(`${this._storageService.getData('user')}`).email,
        this._activeMag.name
      );
      let result = this._magazineService
        .commentMagazine(this._newComment)
        .subscribe(
          (_success: string) => {
            if (_success === 'NO_ERROR') {
              this.showSuccess('comentario registrado');
            } else {
              this.showAlertMsg('no se ha podido publicar tu comentario');
            }
          },
          (_error: Error) => {
            console.log('ERROR: ');
            console.log(_error.message);
          }
        );
    } else {
      this.showAlertMsg('hacen falta datos para ingresar');
    }
  }

  private formValid(): boolean {
    let tmp: boolean =
      this._newComment.text != '' && this._newComment.text.trim() != '';
    if (tmp && this._newComment.dateString.trim() != '') {
      return true;
    } else {
      return false;
    }
  }

  private showSuccess(_message: string) {
    this._showSUccessMsg = true;
    this._successMsg = _message;
  }

  // INFINITE SCROLL
  public onScroll() {
    if (this._actualPage < this._finishPage) {
      this.getCommets();
      this._actualPage++;
    } else {
      console.log('No more lines. Finish page!');
    }
  }

  public scrollTop() {
    document.body.scrollTop = 0; // Safari
    document.documentElement.scrollTop = 0; // Other
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    if (
      (window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop) > this._showScrollHeight
    ) {
      this._showGoUpButton = true;
    } else if (
      this._showGoUpButton &&
      (window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop) < this._hideScrollHeight
    ) {
      this._showGoUpButton = false;
    }
  }
}

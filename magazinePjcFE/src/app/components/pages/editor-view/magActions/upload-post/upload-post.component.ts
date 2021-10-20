import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {
  Magazine,
  MagazinePost,
  MagazinePostMessage,
} from 'src/app/modules/MagazineMessage.module';
import { User } from 'src/app/modules/SignUpMessge.module';
import { LocalStorageService } from 'src/app/services/LocalStorage/local-storage.service';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-upload-post',
  templateUrl: './upload-post.component.html',
  styleUrls: ['./upload-post.component.css'],
})
export class UploadPostComponent implements OnInit {
  public _postForm: FormGroup;
  public _limit: number;
  public _offset: number;
  public _actualPage: number;
  public _finishPage: number = 10;
  public _showScrollHeight: number = 400;
  public _hideScrollHeight: number = 200;
  public _errorMsg: string;
  public _successMsg: string;
  public _showErrorMsg: boolean = false;
  public _showSuccessMsg: boolean = false;
  public _showGoUpButton: boolean;
  private _fileToUpload: File | null = null;
  public _magazines: Array<Magazine>;
  private _postTmp: MagazinePost;
  private _user: User;

  constructor(
    private _magService: MagazineService,
    private _storageService: LocalStorageService
  ) {
    this._postForm = this.generatePostFormGroup();
    this._actualPage = 1;
    this._limit = 10;
    this._offset = 0;
  }

  ngOnInit(): void {
    this._magazines = new Array<Magazine>();
    this._user = JSON.parse(`${this._storageService.getData('user')}`);
    this.getOwnedMagazines();
  }

  // FOR HTML
  public setPDF(_event: Event) {
    const files = (_event.target as HTMLInputElement).files;
    if (files != null) {
      this._fileToUpload = files.item(0);
    }
  }

  public onScroll() {
    if (this._actualPage < this._finishPage) {
      this.getOwnedMagazines();
      this._actualPage++;
    } else {
      console.log('Limit pages exceeed');
    }
  }

  public setMag(_mag: Magazine) {
    this._postForm.controls['_magazine'].setValue(_mag.name);
  }

  public uploadPost() {
    // RESET VALUES
    this._showErrorMsg = false;
    this._showSuccessMsg = false;
    if (this._postForm.valid && this._fileToUpload != null) {
      // generate object
      this.getMagPostObject();
      let _magMessage = new MagazinePostMessage('añ', this._postTmp);
      console.log(_magMessage);

      this._magService.uploadPost(_magMessage, this._fileToUpload).subscribe(
        (_success: MagazinePostMessage) => {
          if (_success.message === 'NO_ERROR') {
            this.showSuccess('Se ha subido el nuevo post');
          } else {
            this.showError('No se ha podido subir tu archivo');
          }
        },
        (_error: Error) => {
          console.log(`ERror:`);
          console.log(_error);
        }
      );
    } else {
      this.showError('Todos los atributos deben ser ingresados');
    }
  }

  //  FOR TS actions
  private generatePostFormGroup(): FormGroup {
    return new FormGroup({
      _id: new FormControl(0),
      _name: new FormControl('', [Validators.required]),
      _date: new FormControl('', [Validators.required]),
      _pdf: new FormControl('', [Validators.required]),
      _magazine: new FormControl('', [Validators.required]),
    });
  }

  private showError(_message: string): void {
    this._errorMsg = _message;
    this._showErrorMsg = true;
  }

  private showSuccess(_message: string): void {
    this._successMsg = _message;
    this._showSuccessMsg = true;
  }

  private getMagPostObject(): void {
    if (this._fileToUpload != null) {
      this._postTmp = new MagazinePost(
        this._postForm.controls['_id'].value,
        this._postForm.controls['_name'].value,
        this._postForm.controls['_date'].value,
        this._fileToUpload,
        this._postForm.controls['_magazine'].value
      );
    }
  }

  private getOwnedMagazines() {
    this._magService
      .getMagazineOwned(this._user.email, this._limit, this._offset)
      .subscribe(
        (_success: Magazine[]) => {
          for (const _mag of _success) {
            this._magazines.push(_mag);
          }
          this._offset += +this._limit;
        },
        (_error: Error) => {
          console.log(_error);
        }
      );
  }

  // INFINITE SCROLL

  scrollTop() {
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

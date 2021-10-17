import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Magazine } from 'src/app/modules/MagazineMessage.module';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-preview-magazine',
  templateUrl: './preview-magazine.component.html',
  styleUrls: ['./preview-magazine.component.css'],
})
export class PreviewMagazineComponent implements OnInit {
  public _activeMag: Magazine;
  public _allowLikesMsg: string;
  public _allowCommentMsg: string;

  constructor(
    private _magazineService: MagazineService,
    private _route: ActivatedRoute
  ) {
    this._activeMag = this.newEmptyMag();
  }

  ngOnInit(): void {
    this.generateMagazine();
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

  private setMessages() {
    this._allowCommentMsg =
      this._activeMag.allowComment === true ? 'recibe' : 'no recibe';
    this._allowLikesMsg =
      this._activeMag.allowLikes === true ? 'recibe' : 'no recibe';
  }

  generateMagazine() {
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
}

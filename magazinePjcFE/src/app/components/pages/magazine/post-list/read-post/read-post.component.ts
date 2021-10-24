import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { MagazinePost } from 'src/app/modules/MagazineMessage.module';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';
import { APIs } from 'src/app/vars/enums/API';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-read-post',
  templateUrl: './read-post.component.html',
  styleUrls: ['./read-post.component.css'],
})
export class ReadPostComponent implements OnInit {
  public _post: MagazinePost;
  public _idUrl: number;
  public _downloadPdf: string;
  public _mapUrl: SafeResourceUrl;
  public _magPreviewLink: SafeResourceUrl;

  constructor(
    private _magService: MagazineService,
    private _route: ActivatedRoute,
    private _sanitizer: DomSanitizer
  ) {
    this._post = new MagazinePost(0, '', '', null, '');
    let tmp = this._route.snapshot.paramMap.get('id');
    if (tmp != null) {
      this._idUrl = +tmp;
    }
  }

  ngOnInit(): void {
    this.getBasicData();
    let _showPdfUrl = `${APIs.FILES_GIVER_CONTROLLER}?action=SHOW_PDF&id=${this._idUrl}`;
    this._downloadPdf = `${APIs.FILES_GIVER_CONTROLLER}?action=DOWNLOAD_PDF&id=${this._idUrl}`;
    this._mapUrl = this._sanitizer.bypassSecurityTrustResourceUrl(_showPdfUrl);
    this._magPreviewLink = Routes.PREVIEW_MAGAZINE;
  }

  private getBasicData() {
    this._magService.getBasicInfoToShowPost(this._idUrl).subscribe(
      (_success: MagazinePost[]) => {
        this._post = _success[0];
      },
      (_error: Error) => {
        console.log('error');
        console.log(_error);
      }
    );
  }
}

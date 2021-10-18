import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MagazinePost } from 'src/app/modules/MagazineMessage.module';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';
import { Routes } from 'src/app/vars/enums/ROUTES';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css'],
})
export class PostListComponent implements OnInit {
  // VARS
  public _magazine: string;
  public _posts: Array<MagazinePost>;
  public _seePdfUrl: string = Routes.SEE_MAG_POST;
  // INIFINITE SCROLL
  public _actualPage: number;
  public _offset: number;
  public _limit: number = 20;
  public _finishPage: number = 20;
  public _showScrollHeight: number = 400;
  public _hideScrollHeight: number = 200;
  public _showGoUpButton: boolean;

  constructor(
    private _route: ActivatedRoute,
    private _magazineService: MagazineService
  ) {
    this._magazine = `${this._route.snapshot.paramMap.get('magazine')}`;
    this._offset = 0;
    this._actualPage = 1;
    this._posts = new Array<MagazinePost>();
  }

  ngOnInit(): void {
    this.getPosts();
  }

  // FOR TS WORKOUT
  private getPosts(): void {
    this._magazineService
      .getPosts(this._magazine, this._limit, this._offset)
      .subscribe((_success: MagazinePost[]) => {
        for (const _post of _success) {
          this._posts.push(_post);
        }
        this._offset += +this._limit;
        this._actualPage++;
      });
  }

  // INFINITE SCROLL
  public onScroll() {
    if (this._actualPage < this._finishPage) {
      this.getPosts();
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

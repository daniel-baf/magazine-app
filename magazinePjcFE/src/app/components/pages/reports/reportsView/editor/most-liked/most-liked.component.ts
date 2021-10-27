import { Component, Input, OnInit } from '@angular/core';
import { MagazineLikesReport } from 'src/app/modules/HTMLJasperReprots.module';
import { HTMLReports } from 'src/app/services/HTMLReports.service';

@Component({
  selector: 'app-most-liked',
  templateUrl: './most-liked.component.html',
  styleUrls: ['./most-liked.component.css'],
})
export class MostLikedComponent implements OnInit {
  @Input() dateStart: string;
  @Input() dateEnd: string;
  @Input() email: string;
  public _magLikes: MagazineLikesReport[];

  constructor(private _jasperReport: HTMLReports) {}

  ngOnInit(): void {
    this.getHTMLInfo();
  }

  private getHTMLInfo() {
    this._jasperReport
      .getMagLikes(this.dateStart, this.dateEnd, this.email)
      .subscribe((_success: MagazineLikesReport[]) => {
        this._magLikes = _success;
      });
  }
}

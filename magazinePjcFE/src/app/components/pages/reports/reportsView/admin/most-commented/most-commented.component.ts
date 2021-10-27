import { Component, Input, OnInit } from '@angular/core';
import { MagazineCommentsReport } from 'src/app/modules/HTMLJasperReprots.module';
import { HTMLReports } from 'src/app/services/HTMLReports.service';

@Component({
  selector: 'app-most-commented',
  templateUrl: './most-commented.component.html',
  styleUrls: ['./most-commented.component.css'],
})
export class MostCommentedComponent implements OnInit {
  @Input() dateStart: string;
  @Input() dateEnd: string;
  public _commentsReport: MagazineCommentsReport[];
  constructor(private _httpReport: HTMLReports) {}

  ngOnInit(): void {
    this.getList();
  }

  public getList() {
    this._httpReport
      .getMostCommentedMags(this.dateStart, this.dateEnd)
      .subscribe((_success: MagazineCommentsReport[]) => {
        this._commentsReport = _success;
      });
  }
}

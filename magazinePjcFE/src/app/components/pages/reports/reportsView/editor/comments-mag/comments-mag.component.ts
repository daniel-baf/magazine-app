import { Component, Input, OnInit } from '@angular/core';
import { MagazineCommentsReport } from 'src/app/modules/HTMLJasperReprots.module';
import { HTMLReports } from 'src/app/services/HTMLReports.service';

@Component({
  selector: 'app-comments-mag',
  templateUrl: './comments-mag.component.html',
  styleUrls: ['./comments-mag.component.css'],
})
export class CommentsMagComponent implements OnInit {
  @Input() dateStart: string;
  @Input() dateEnd: string;
  @Input() email: string;
  public magReport: Array<MagazineCommentsReport>;

  constructor(private _htmlJasper: HTMLReports) {}

  ngOnInit(): void {
    this.getMagCommentsEditor();
  }

  private getMagCommentsEditor() {
    this._htmlJasper
      .getJSONMagComments(
        this.dateStart,
        this.dateEnd,
        'comments-mag',
        this.email
      )
      .subscribe((_success: MagazineCommentsReport[]) => {
        this.magReport = _success;
      });
  }
}

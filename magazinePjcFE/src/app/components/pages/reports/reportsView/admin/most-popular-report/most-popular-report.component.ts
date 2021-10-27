import { Component, Input, OnInit } from '@angular/core';
import { MagazineSubscriptionReport } from 'src/app/modules/HTMLJasperReprots.module';
import { HTMLReports } from 'src/app/services/HTMLReports.service';

@Component({
  selector: 'app-most-popular-report',
  templateUrl: './most-popular-report.component.html',
  styleUrls: ['./most-popular-report.component.css'],
})
export class MostPopularReportComponent implements OnInit {
  @Input() dateStart: string;
  @Input() dateEnd: string;
  public _magazineSubs: MagazineSubscriptionReport[];

  constructor(public _HTMLReport: HTMLReports) {}

  ngOnInit(): void {
    this.getList();
  }

  public getList() {
    this._HTMLReport
      .getMostPopularMags(this.dateStart, this.dateEnd)
      .subscribe((_success: MagazineSubscriptionReport[]) => {
        this._magazineSubs = _success;
      });
  }
}

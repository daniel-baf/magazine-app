import { Component, Input, OnInit } from '@angular/core';
import { EarningsResultReport } from 'src/app/modules/HTMLJasperReprots.module';
import { HTMLReports } from 'src/app/services/HTMLReports.service';

@Component({
  selector: 'app-total-earnings-report',
  templateUrl: './total-earnings-report.component.html',
  styleUrls: ['./total-earnings-report.component.css'],
})
export class TotalEarningsReportComponent implements OnInit {
  @Input() dateStart: string;
  @Input() dateEnd: string;
  public _earningsReport: EarningsResultReport[];
  public _entryTotal: number = 0;
  public _lossTotal: number = 0;

  constructor(private _httpReport: HTMLReports) {}

  ngOnInit(): void {
    this.getList();
  }

  public getList() {
    this._httpReport
      .getTotalEarnings(this.dateStart, this.dateEnd)
      .subscribe((_success: EarningsResultReport[]) => {
        this._earningsReport = _success;
        this.getTotal();
      });
  }

  public getTotal() {
    this._earningsReport.forEach((_earning) => {
      this._entryTotal += +_earning.entry;
      this._lossTotal += +_earning.loss;
    });
  }
}

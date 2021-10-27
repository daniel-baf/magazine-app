import { Component, Input, OnInit } from '@angular/core';
import { CompanyEarningByMag } from 'src/app/modules/HTMLJasperReprots.module';
import { HTMLReports } from 'src/app/services/HTMLReports.service';

@Component({
  selector: 'app-mag-earns-cpny',
  templateUrl: './mag-earns-cpny.component.html',
  styleUrls: ['./mag-earns-cpny.component.css'],
})
export class MagEarnsCpnyComponent implements OnInit {
  @Input() dateStart: string;
  @Input() dateEnd: string;
  public _magsEarnings: CompanyEarningByMag[];
  public _total: number = 0;

  constructor(private _htmlReport: HTMLReports) {}

  ngOnInit(): void {
    this.getList();
  }

  public getList() {
    this._htmlReport
      .getEarnigsByMags(this.dateStart, this.dateEnd)
      .subscribe((_success: CompanyEarningByMag[]) => {
        this._magsEarnings = _success;
        this.getTotal();
      });
  }

  public getTotal() {
    this._magsEarnings.forEach((_mag) => {
      _mag.subscriptions.forEach((_sub) => {
        this._total += +_sub.fee;
      });
    });
  }
}

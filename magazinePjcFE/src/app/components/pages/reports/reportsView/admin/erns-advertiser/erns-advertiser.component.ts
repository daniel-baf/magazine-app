import { Component, Input, OnInit } from '@angular/core';
import { EarningByAdvertiser } from 'src/app/modules/HTMLJasperReprots.module';
import { HTMLReports } from 'src/app/services/HTMLReports.service';

@Component({
  selector: 'app-erns-advertiser',
  templateUrl: './erns-advertiser.component.html',
  styleUrls: ['./erns-advertiser.component.css'],
})
export class ErnsAdvertiserComponent implements OnInit {
  @Input() dateStart: string;
  @Input() dateEnd: string;
  public _adsReport: EarningByAdvertiser[];
  public _total: number = 0;

  constructor(private _htmlReport: HTMLReports) {}

  ngOnInit(): void {
    this.getList();
  }

  public getList() {
    this._htmlReport
      .getEarningsByAdvertiser(this.dateStart, this.dateEnd)
      .subscribe((_success: EarningByAdvertiser[]) => {
        this._adsReport = _success;
        this.getTotal();
      });
  }

  public getTotal() {
    this._adsReport.forEach((_advertiser) => {
      _advertiser.ads.forEach((_ad) => {
        this._total += +_ad.advertiserPaid;
      });
    });
  }
}

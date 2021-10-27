import { Component, Input, OnInit } from '@angular/core';
import { MagazineSubscriptionReport } from 'src/app/modules/HTMLJasperReprots.module';
import { HTMLReports } from 'src/app/services/HTMLReports.service';

@Component({
  selector: 'app-subs-mag',
  templateUrl: './subs-mag.component.html',
  styleUrls: ['./subs-mag.component.css'],
})
export class SubsMagComponent implements OnInit {
  public _magazineSubs: MagazineSubscriptionReport[];

  @Input() dateStart: string;
  @Input() dateEnd: string;
  @Input() email: string;

  constructor(private _jasperHTML: HTMLReports) {}

  ngOnInit(): void {
    this.getHtmlpSUbParts();
  }

  private getHtmlpSUbParts() {
    console.log(this.dateEnd);
    console.log(this.dateStart);
    console.log(this.email);

    this._jasperHTML
      .getMagSubscriptions(this.dateStart, this.dateEnd, this.email)
      .subscribe((_success: MagazineSubscriptionReport[]) => {
        this._magazineSubs = _success;
      });
  }
}

import { Component, Input, OnInit } from '@angular/core';
import { EditorEarningReport } from 'src/app/modules/HTMLJasperReprots.module';
import { HTMLReports } from 'src/app/services/HTMLReports.service';

@Component({
  selector: 'app-editor-earns',
  templateUrl: './editor-earns.component.html',
  styleUrls: ['./editor-earns.component.css'],
})
export class EditorEarnsComponent implements OnInit {
  @Input() dateStart: string;
  @Input() dateEnd: string;
  @Input() email: string;
  public _editorEarnings: EditorEarningReport[];
  public _total: number = 0;

  constructor(private _jasperHTML: HTMLReports) {}

  ngOnInit(): void {
    this.getListHTML();
  }

  private getListHTML() {
    this._jasperHTML
      .getEditorEarningsRep(this.dateStart, this.dateEnd, this.email)
      .subscribe((_success: EditorEarningReport[]) => {
        this._editorEarnings = _success;
        this.calculateTotal();
      });
  }

  private calculateTotal() {
    this._editorEarnings.forEach((_earn) => {
      _earn.subs.forEach((_sub) => {
        this._total += +_sub.fee;
      });
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { Magazine } from 'src/app/modules/Magazine/Magazine.module';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-preview-magazine',
  templateUrl: './preview-magazine.component.html',
  styleUrls: ['./preview-magazine.component.css'],
})
export class PreviewMagazineComponent implements OnInit {
  public _activeMag: Magazine;

  constructor(private _magazineService: MagazineService) {
    this._activeMag = this.newEmptyMag();
  }

  ngOnInit(): void {
    this.generateMagazine();
  }

  public newEmptyMag(): Magazine {
    return new Magazine(
      '',
      0,
      0,
      0,
      '0000-00-00',
      '',
      false,
      false,
      '',
      '',
      false,
      []
    );
  }

  generateMagazine() {
    this._magazineService.getMagazine(this._activeMag.name).subscribe(
      (_success: Magazine[]) => {
        this._activeMag = _success[0];
      },
      (_error: Error) => {
        console.log(_error);
      }
    );
  }
}

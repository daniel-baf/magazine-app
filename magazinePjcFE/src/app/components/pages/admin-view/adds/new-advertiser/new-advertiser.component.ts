import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AddsService } from 'src/app/services/Financial/ads.service';

@Component({
  selector: 'app-new-advertiser',
  templateUrl: './new-advertiser.component.html',
  styleUrls: ['./new-advertiser.component.css'],
})
export class NewAdvertiserComponent implements OnInit {
  //alerts
  _showSuccessMsg: boolean = false;
  _showErrorMsg: boolean = false;
  _successMsg: string;
  _errorMsg: string;
  // formgroup
  _advertiserForm: FormGroup = new FormGroup({
    _name: new FormControl('', [Validators.required]),
  });

  constructor(private _addsService: AddsService) {}

  ngOnInit(): void {}

  // FORM
  public sendForm() {
    if (this._advertiserForm.valid) {
      this._addsService
        .createAdvertiser(this._advertiserForm.controls['_name'].value)
        .subscribe(
          (_success: string) => {
            if (_success == 'NO_ERROR') {
              this.showSuccess('Se ha creado el nuevo anunciante');
              
            } else {
              this.showError(
                'No se puede insertar al usuario, posible duplicado'
              );
            }
          },
          (_error: Error) => {
            console.log('ERror');
            console.log(_error);
          }
        );
    } else {
      this.showError('Valor invalido');
    }
  }

  // Messages
  private showError(_error: string) {
    this._showErrorMsg = true;
    this._errorMsg = _error;
  }

  private showSuccess(_message: string) {
    this._showSuccessMsg = true;
    this._successMsg = _message;
  }
}

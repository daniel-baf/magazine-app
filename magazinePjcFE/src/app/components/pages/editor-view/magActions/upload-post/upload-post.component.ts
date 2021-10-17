import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import {
  MagazinePost,
  MagazinePostMessage,
} from 'src/app/modules/MagazineMessage.module';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-upload-post',
  templateUrl: './upload-post.component.html',
  styleUrls: ['./upload-post.component.css'],
})
export class UploadPostComponent implements OnInit {
  public _postForm: FormGroup;
  public _errorMsg: string;
  public _successMsg: string;
  public _showErrorMsg: boolean = false;
  public _showSuccessMsg: boolean = false;
  private _fileToUpload: File | null = null;
  private _postTmp: MagazinePost;

  constructor(
    private _magService: MagazineService,
    private sanitizer: DomSanitizer
  ) {
    this._postForm = this.generatePostFormGroup();
  }

  ngOnInit(): void {}

  // FOR HTML
  public setPDF(_event: Event) {
    const files = (_event.target as HTMLInputElement).files;
    if (files != null) {
      this._fileToUpload = files.item(0);
    }
  }

  public uploadPost() {
    // RESET VALUES
    this._showErrorMsg = false;
    this._showSuccessMsg = false;
    if (this._postForm.valid && this._fileToUpload != null) {
      // generate object
      this.getMagPostObject();
      let _magMessage = new MagazinePostMessage('UPLOAD', this._postTmp);
      this._magService.uploadPost(_magMessage, this._fileToUpload).subscribe(
        (_success: MagazinePostMessage) => {
          console.log('recibido: ');
          console.log(_success);
        },
        (_error: Error) => {
          console.log(`ERror:`);
          console.log(_error);
        }
      );
      this.showSuccess('enviando');
    } else {
      this.showError('Todos los atributos deben ser ingresados');
    }
  }

  //  FOR TS actions
  private generatePostFormGroup(): FormGroup {
    return new FormGroup({
      _id: new FormControl(0),
      _name: new FormControl('', [Validators.required]),
      _date: new FormControl('', [Validators.required]),
      _pdf: new FormControl('', [Validators.required]),
      _magazine: new FormControl('', [Validators.required]),
    });
  }

  private showError(_message: string): void {
    this._errorMsg = _message;
    this._showErrorMsg = true;
  }

  private showSuccess(_message: string): void {
    this._successMsg = _message;
    this._showSuccessMsg = true;
  }

  private getMagPostObject(): void {
    if (this._fileToUpload != null) {
      this._postTmp = new MagazinePost(
        this._postForm.controls['_id'].value,
        this._postForm.controls['_name'].value,
        this._postForm.controls['_date'].value,
        this._postForm.controls['_magazine'].value
      );
    }
  }
}

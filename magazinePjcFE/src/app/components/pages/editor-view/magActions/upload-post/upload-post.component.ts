import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-upload-post',
  templateUrl: './upload-post.component.html',
  styleUrls: ['./upload-post.component.css'],
})
export class UploadPostComponent implements OnInit {
  public _postForm: FormGroup;

  constructor() {
    this._postForm = this.generatePostFormGroup();
  }

  ngOnInit(): void {}

  // FOR HTML
  public uploadPost() {
    console.log(this._postForm);
  }

  //  FOR TS actions
  private generatePostFormGroup(): FormGroup {
    return new FormGroup({
      _id: new FormControl(''),
      _name: new FormControl('', [Validators.required]),
      _date: new FormControl('', [Validators.required]),
      _pdf: new FormControl('', [Validators.required]),
      _magazine: new FormControl('', [Validators.required]),
    });
  }
}

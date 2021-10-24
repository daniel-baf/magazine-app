import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/modules/SignUpMessge.module';
import { LoginService } from 'src/app/services/Logs/login.service';
import { APIs } from 'src/app/vars/enums/API';

@Component({
  selector: 'app-public-editor-profile',
  templateUrl: './public-editor-profile.component.html',
  styleUrls: ['./public-editor-profile.component.css'],
})
export class PublicEditorProfileComponent implements OnInit {
  public _user: User;
  public _urlPicProfile: SafeResourceUrl;

  constructor(
    private _userService: LoginService,
    private _route: ActivatedRoute,
    private _sanitizer: DomSanitizer
  ) {
    this._user = new User('', '');
    this._urlPicProfile = this._sanitizer.bypassSecurityTrustResourceUrl(
      `${
        APIs.FILES_GIVER_CONTROLLER
      }?action=GET_PROF_PIC&type=EDITOR&user=${this._route.snapshot.paramMap.get(
        'user'
      )}`
    );
    this.callInfo();
  }

  ngOnInit(): void {
    this.callBasicInfo();
  }

  private callInfo() {
    this._user.email = `${this._route.snapshot.paramMap.get('user')}`;
  }

  private callBasicInfo() {
    this._userService
      .getEditorPublic(this._user.email)
      .subscribe((_success: User) => {
        this._user = _success;
      });
  }
}

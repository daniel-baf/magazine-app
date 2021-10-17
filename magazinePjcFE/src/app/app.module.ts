import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './components/logPage/signup/signup.component';
import { LogNavComponent } from './components/logPage/log-nav/log-nav.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/logPage/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { EditorViewComponent } from './components/pages/editor-view/editor-view.component';
import { AdminViewComponent } from './components/pages/admin-view/admin-view.component';
import { ReaderViewComponent } from './components/pages/reader-view/reader-view.component';
import { SelectCategoriesComponent } from './components/logPage/signup/select-categories/select-categories.component';
import { EditProfileComponent } from './components/pages/edit-profile/edit-profile.component';
import { NavViewPageComponent } from './components/pages/nav-view-page/nav-view-page.component';
import { FooterComponent } from './components/pages/footer/footer.component';
import { MainComponent } from './components/logPage/main/main.component';
import { PagesComponent } from './components/pages/pages.component';
import { EditorAsideComponent } from './components/pages/editor-view/editor-aside/editor-aside.component';
import { NewMagazineComponent } from './components/pages/editor-view/magActions/new-magazine/new-magazine.component';
import { ApproveMagComponent } from './components/pages/admin-view/approve-mag/approve-mag.component';
import { PreviewMagazineComponent } from './components/pages/magazine/preview-magazine/preview-magazine.component';
import { MagazineListComponent } from './components/pages/reader-view/magazine-list/magazine-list.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { UploadPostComponent } from './components/pages/editor-view/magActions/upload-post/upload-post.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LogNavComponent,
    LoginComponent,
    EditorViewComponent,
    AdminViewComponent,
    ReaderViewComponent,
    SelectCategoriesComponent,
    EditProfileComponent,
    NavViewPageComponent,
    FooterComponent,
    MainComponent,
    PagesComponent,
    EditorAsideComponent,
    NewMagazineComponent,
    ApproveMagComponent,
    PreviewMagazineComponent,
    MagazineListComponent,
    UploadPostComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    InfiniteScrollModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}

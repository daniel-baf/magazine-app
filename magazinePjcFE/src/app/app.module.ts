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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './components/error/error.component';
import { LoginComponent } from './components/logPage/login/login.component';
import { SelectCategoriesComponent } from './components/logPage/signup/select-categories/select-categories.component';
import { SignupComponent } from './components/logPage/signup/signup.component';
import { AdminViewComponent } from './components/pages/admin-view/admin-view.component';
import { EditProfileComponent } from './components/pages/edit-profile/edit-profile.component';
import { EditorViewComponent } from './components/pages/editor-view/editor-view.component';
import { ReaderViewComponent } from './components/pages/reader-view/reader-view.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'signup/select-categories/', component: SelectCategoriesComponent },
  { path: 'pages/admin-view', component: AdminViewComponent },
  { path: 'pages/reader-view', component: ReaderViewComponent },
  { path: 'pages/editor-view', component: EditorViewComponent },
  { path: 'pages/edit-profile', component: EditProfileComponent },
  { path: '**', component: ErrorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

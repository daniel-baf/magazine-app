import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './components/error/error.component';
import { LoginComponent } from './components/logPage/login/login.component';
import { MainComponent } from './components/logPage/main/main.component';
import { SelectCategoriesComponent } from './components/logPage/signup/select-categories/select-categories.component';
import { SignupComponent } from './components/logPage/signup/signup.component';
import { ApproveMagComponent } from './components/pages/admin-view/approve-mag/approve-mag.component';
import { EditProfileComponent } from './components/pages/edit-profile/edit-profile.component';
import { NewMagazineComponent } from './components/pages/editor-view/new-magazine/new-magazine.component';
import { PreviewMagazineComponent } from './components/pages/magazine/preview-magazine/preview-magazine.component';
import { PagesComponent } from './components/pages/pages.component';

const routes: Routes = [
  { path: '', component: MainComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  {
    path: 'pages',
    component: PagesComponent,
    children: [
      {
        path: 'edit-profile',
        component: EditProfileComponent,
      },
      {
        path: 'editor-view',
        children: [
          { path: 'new-mag', component: NewMagazineComponent },
          {
            path: 'select-categories',
            component: SelectCategoriesComponent,
          },
        ],
      },
      {
        path: 'admin-view',
        children: [
          {
            path: 'approve-mag',
            component: ApproveMagComponent,
          },
        ],
      },
      {
        path: 'magazine',
        children: [
          { path: 'preview/:name', component: PreviewMagazineComponent },
        ],
      },
    ],
  },
  { path: '**', component: ErrorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

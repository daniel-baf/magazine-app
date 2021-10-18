import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './components/error/error.component';
import { LoginComponent } from './components/logPage/login/login.component';
import { MainComponent } from './components/logPage/main/main.component';
import { SelectCategoriesComponent } from './components/logPage/signup/select-categories/select-categories.component';
import { SignupComponent } from './components/logPage/signup/signup.component';
import { ApproveMagComponent } from './components/pages/admin-view/approve-mag/approve-mag.component';
import { EditProfileComponent } from './components/pages/edit-profile/edit-profile.component';
import { NewMagazineComponent } from './components/pages/editor-view/magActions/new-magazine/new-magazine.component';
import { UploadPostComponent } from './components/pages/editor-view/magActions/upload-post/upload-post.component';
import { PostListComponent } from './components/pages/magazine/post-list/post-list.component';
import { ReadPostComponent } from './components/pages/magazine/post-list/read-post/read-post.component';
import { PreviewMagazineComponent } from './components/pages/magazine/preview-magazine/preview-magazine.component';
import { PagesComponent } from './components/pages/pages.component';
import { ReadMagComponent } from './components/pages/reader-view/mag-reader-options/read-mag/read-mag.component';
import { MagazineListComponent } from './components/pages/reader-view/magazine-list/magazine-list.component';

const routes: Routes = [
  { path: '', component: MainComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  {
    path: 'pages',
    component: PagesComponent,
    children: [
      {
        path: 'reader-view',
        children: [
          {
            path: 'mgrdoptn',
            children: [
              { path: 'mag-list', component: MagazineListComponent },
              { path: 'read-mag', component: ReadMagComponent },
            ],
          },
        ],
      },
      {
        path: 'editor-view',
        children: [
          {
            path: 'mag',
            children: [
              { path: 'new-mag', component: NewMagazineComponent },
              { path: 'upload-post', component: UploadPostComponent },
            ],
          },
          { path: 'select-categories', component: SelectCategoriesComponent },
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
          { path: 'post-list/:magazine', component: PostListComponent },
          { path: 'read-post/:id', component: ReadPostComponent },
        ],
      },
      {
        path: 'edit-profile',
        component: EditProfileComponent,
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

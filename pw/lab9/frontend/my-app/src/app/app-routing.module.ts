import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllDocumentsComponent } from './all-documents/all-documents.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {
    path: 'allDocuments',
    component: AllDocumentsComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllDocumentsComponent } from './all-documents/all-documents.component';

const routes: Routes = [
  {
    path: 'allDocuments',
    component: AllDocumentsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

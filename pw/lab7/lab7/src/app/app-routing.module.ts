import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DeleteDocumentComponent } from './delete-document/delete-document.component';
import { ShowDocumentsComponent } from './show-documents/show-documents.component';

const routes: Routes = [
  { path: 'deleteDocument', component: DeleteDocumentComponent },
  { path: 'showDocuments', component: ShowDocumentsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

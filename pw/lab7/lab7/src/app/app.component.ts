import { Component, OnInit } from '@angular/core';
import { DocumentService } from 'src/api/documentsApi/document.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  constructor(private documentService: DocumentService) {}
  documents: Document[] = [];

  ngOnInit(): void {
    this.documentService.getAllDocuments().subscribe((documents) => {});
  }
  title = 'All documents';
}

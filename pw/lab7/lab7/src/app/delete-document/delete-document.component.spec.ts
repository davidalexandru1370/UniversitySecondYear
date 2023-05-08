import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteDocumentComponent } from './delete-document.component';

describe('DeleteDocumentComponent', () => {
  let component: DeleteDocumentComponent;
  let fixture: ComponentFixture<DeleteDocumentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeleteDocumentComponent]
    });
    fixture = TestBed.createComponent(DeleteDocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

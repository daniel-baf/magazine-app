import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicEditorProfileComponent } from './public-editor-profile.component';

describe('PublicEditorProfileComponent', () => {
  let component: PublicEditorProfileComponent;
  let fixture: ComponentFixture<PublicEditorProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicEditorProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicEditorProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

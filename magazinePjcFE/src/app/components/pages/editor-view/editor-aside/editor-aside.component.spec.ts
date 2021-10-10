import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorAsideComponent } from './editor-aside.component';

describe('EditorAsideComponent', () => {
  let component: EditorAsideComponent;
  let fixture: ComponentFixture<EditorAsideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditorAsideComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorAsideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

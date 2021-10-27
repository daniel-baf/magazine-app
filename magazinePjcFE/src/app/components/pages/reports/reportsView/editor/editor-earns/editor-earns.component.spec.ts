import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorEarnsComponent } from './editor-earns.component';

describe('EditorEarnsComponent', () => {
  let component: EditorEarnsComponent;
  let fixture: ComponentFixture<EditorEarnsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditorEarnsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorEarnsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

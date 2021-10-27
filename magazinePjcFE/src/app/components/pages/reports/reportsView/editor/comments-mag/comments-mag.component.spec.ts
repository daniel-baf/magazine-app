import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentsMagComponent } from './comments-mag.component';

describe('CommentsMagComponent', () => {
  let component: CommentsMagComponent;
  let fixture: ComponentFixture<CommentsMagComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentsMagComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentsMagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

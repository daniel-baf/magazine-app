import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveMagComponent } from './approve-mag.component';

describe('ApproveMagComponent', () => {
  let component: ApproveMagComponent;
  let fixture: ComponentFixture<ApproveMagComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApproveMagComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveMagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

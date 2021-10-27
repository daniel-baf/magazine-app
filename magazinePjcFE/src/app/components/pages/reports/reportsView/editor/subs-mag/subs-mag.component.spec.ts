import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubsMagComponent } from './subs-mag.component';

describe('SubsMagComponent', () => {
  let component: SubsMagComponent;
  let fixture: ComponentFixture<SubsMagComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubsMagComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubsMagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

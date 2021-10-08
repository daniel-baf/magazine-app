import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavViewPageComponent } from './nav-view-page.component';

describe('NavViewPageComponent', () => {
  let component: NavViewPageComponent;
  let fixture: ComponentFixture<NavViewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavViewPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavViewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

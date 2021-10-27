import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TotalEarningsReportComponent } from './total-earnings-report.component';

describe('TotalEarningsReportComponent', () => {
  let component: TotalEarningsReportComponent;
  let fixture: ComponentFixture<TotalEarningsReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TotalEarningsReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TotalEarningsReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

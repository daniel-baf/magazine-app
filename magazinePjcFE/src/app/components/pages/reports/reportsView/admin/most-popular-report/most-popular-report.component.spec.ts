import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostPopularReportComponent } from './most-popular-report.component';

describe('MostPopularReportComponent', () => {
  let component: MostPopularReportComponent;
  let fixture: ComponentFixture<MostPopularReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MostPopularReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MostPopularReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

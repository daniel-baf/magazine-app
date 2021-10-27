import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MagEarnsCpnyComponent } from './mag-earns-cpny.component';

describe('MagEarnsCpnyComponent', () => {
  let component: MagEarnsCpnyComponent;
  let fixture: ComponentFixture<MagEarnsCpnyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MagEarnsCpnyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MagEarnsCpnyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

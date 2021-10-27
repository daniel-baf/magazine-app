import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErnsAdvertiserComponent } from './erns-advertiser.component';

describe('ErnsAdvertiserComponent', () => {
  let component: ErnsAdvertiserComponent;
  let fixture: ComponentFixture<ErnsAdvertiserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErnsAdvertiserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ErnsAdvertiserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

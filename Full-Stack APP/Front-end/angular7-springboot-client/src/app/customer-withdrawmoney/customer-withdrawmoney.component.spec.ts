import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerWithdrawmoneyComponent } from './customer-withdrawmoney.component';

describe('CustomerWithdrawmoneyComponent', () => {
  let component: CustomerWithdrawmoneyComponent;
  let fixture: ComponentFixture<CustomerWithdrawmoneyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerWithdrawmoneyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerWithdrawmoneyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

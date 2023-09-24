import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarkBillPayComponent } from './mark-bill-pay.component';

describe('MarkBillPayComponent', () => {
  let component: MarkBillPayComponent;
  let fixture: ComponentFixture<MarkBillPayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MarkBillPayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MarkBillPayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

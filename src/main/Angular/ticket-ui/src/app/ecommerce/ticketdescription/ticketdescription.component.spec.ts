import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketdescriptionComponent } from './ticketdescription.component';

describe('TicketdescriptionComponent', () => {
  let component: TicketdescriptionComponent;
  let fixture: ComponentFixture<TicketdescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TicketdescriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketdescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

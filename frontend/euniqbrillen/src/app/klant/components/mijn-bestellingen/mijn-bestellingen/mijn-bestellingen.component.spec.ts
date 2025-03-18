import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MijnBestellingenComponent } from './mijn-bestellingen.component';

describe('MijnBestellingenComponent', () => {
  let component: MijnBestellingenComponent;
  let fixture: ComponentFixture<MijnBestellingenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MijnBestellingenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MijnBestellingenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

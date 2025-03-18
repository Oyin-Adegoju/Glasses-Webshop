import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BestellingenComponent } from './bestellingen.component';

describe('BestellingenComponent', () => {
  let component: BestellingenComponent;
  let fixture: ComponentFixture<BestellingenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BestellingenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BestellingenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

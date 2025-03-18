import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WinkelwagenArtikelenComponent } from './winkelwagen-artikelen.component';

describe('WinkelwagenArtikelenComponent', () => {
  let component: WinkelwagenArtikelenComponent;
  let fixture: ComponentFixture<WinkelwagenArtikelenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WinkelwagenArtikelenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WinkelwagenArtikelenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

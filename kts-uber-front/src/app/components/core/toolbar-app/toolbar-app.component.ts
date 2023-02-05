import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-toolbar-app',
  templateUrl: './toolbar-app.component.html',
  styleUrls: ['./toolbar-app.component.scss']
})
export class ToolbarAppComponent implements OnInit {
  @Output() refresh = new EventEmitter<void>();

  refreshParent(): void {
    this.refresh.emit();
  }

  constructor(
    public authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  logout(): void {
    this.authService.logout();
    this.refreshParent();
  }
}

import { WebHwPage } from './app.po';

describe('web-hw App', () => {
  let page: WebHwPage;

  beforeEach(() => {
    page = new WebHwPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});

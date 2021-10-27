export enum APIs {
  BACKEND = 'http://localhost:8080/Magazine-Api/',
  // LOG
  CHECK_USER = 'http://localhost:8080/Magazine-Api/UserChecker',
  SIGNUP = 'http://localhost:8080/Magazine-Api/SignUpController',
  // USER
  CATEGORY_CRLLER = 'http://localhost:8080/Magazine-Api/CategoriesSelectContoller',
  SAVE_USER_CATEGORY = 'http://localhost:8080/Magazine-Api',
  UPDATE_USER = 'http://localhost:8080/Magazine-Api/UserUpdater',
  // magazines
  MAGAZINE_CONTOLLER = 'http://localhost:8080/Magazine-Api/MagazineContoller',
  FEES_CONTROLLER = 'http://localhost:8080/Magazine-Api/CompanyFeeController',
  SUBSCRIPTION_CONTOLLER = 'http://localhost:8080/Magazine-Api/SubscriptionController',
  MAGAZINE_POST_CONTROLLER = 'http://localhost:8080/Magazine-Api/MagazinePostController',
  MAGAZINE_REACTIONS_CONTROLLER = 'http://localhost:8080/Magazine-Api/MagazineReactionsController',
  // FILES
  FILES_GIVER_CONTROLLER = 'http://localhost:8080/Magazine-Api/FileGiver',
  JASPER_REPORT_CONTROLLER = 'http://localhost:8080/Magazine-Api/JasperReport',
  // ADMIN
  ADS_CONTROLLER = 'http://localhost:8080/Magazine-Api/AdsController',
  // JASPER
  EDITOR_HTML_REPORT = 'http://localhost:8080/Magazine-Api/HTMLReportEditor',
}

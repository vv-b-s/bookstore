# Project deployment

This project works together with the backend module.
By default the backend listens on `http://localhost:8080/api`.
If you want to change the backend, edit `appsettings.json`'s `BackendAPI` property.

To deploy the application do the following steps:

1) Open console in the `asp-net/Bookstore` folder
2) Execute `dotnet restore` to download project dependencies
3) Execute `dotnet run` to run the application
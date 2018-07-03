// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  authClientID: 'p8686zzvDeoUO4jEHL35MqbPRgdav8N8',
  authDomain: 'unq-desa-grupoi.auth0.com',
  authResponseType: 'token id_token',
  authAudience: 'http://localhost:9090',
  authRedirectUri: 'http://localhost:4200/frontend/auth',
  authScope: 'openid email profile',
  backendUrl: 'http://localhost:9090/backend/'
};

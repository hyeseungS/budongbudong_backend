/* eslint-env node */

module.exports = {
  root: true,
  extends: ['plugin:vue/vue3-essential', 'eslint:recommended', 'prettier'],
  env: {
    'vue/setup-compiler-macros': true,
    node: true, //process오류
    browser: true
  },
  rules: {
    'prefer-promise-reject-errors': 'off',

    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    // allow debugger during development only
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off'
  }
}

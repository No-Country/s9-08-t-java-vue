/** @type {import('tailwindcss').Config} */
const FormKitVariants = require('@formkit/themes/tailwindcss')

export default {
  content: [
    './index.html',
    './src/**/*.{vue,js,ts,jsx,tsx}',
    './node_modules/@formkit/themes/dist/tailwindcss/genesis/index.cjs'
  ],
  theme: {
    extend: {
      colors: {
        'primary-blue': '#2825E4',
        'primary-orange': '#FF6935',
        'primary-text': '#100E34',
        'blue-night': '#291D89'
      },
      screen: {
        'mv' : {'max' : '550px'}
      }
    }
  },
  plugins: [FormKitVariants]
}

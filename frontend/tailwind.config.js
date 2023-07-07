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
        'blue-night': '#291D89'
      }
    }
  },
  plugins: [FormKitVariants]
}

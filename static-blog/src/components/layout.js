/**
 * Layout component that queries for data
 * with Gatsby's useStaticQuery component
 *
 * See: https://www.gatsbyjs.com/docs/use-static-query/
 */

import * as React from "react"
import PropTypes from "prop-types"
import Helmet from 'react-helmet'

import Header from "./header"
import "./layout.css"

const Layout = ({ children }) => (
  <div>
    <Helmet
      title="Static Blog"
      meta={[
        { name: 'description', content: 'Sample' }, 
        { name: 'keywords', content: 'sample, something' },
      ]}
    />
    <Header />

    <div
      style={{
        margin: '0 auto',
        maxWidth: 960,
        padding: '0px 1.0875rem 1.45rem',
        paddingTop: 0,
      }}
    >
      {children}
    </div>
  </div>
)

Layout.propTypes = {
  children: PropTypes.node.isRequired,
}

export default Layout

import React from 'react'
import Link from 'gatsby-link'

const Header = () => (
    <div
        style={{
            background: '#882cb0',
            marginBottom: '1.45rem',
            marginTop:'0px',
            display:'block',
            boxShadow:'0px 0px 7px gray', 
        }}
    >
        <div
            style={{
                margin: '0 auto',
                maxWidth: 960,
                padding: '1.45rem 1.0875rem',
            }}
        >
            <h1 style={{ margin: 0, textAlign:'center' }}>
                <Link
                    to="/"
                    style={{
                        color: 'white',
                        textDecoration: 'none',
                        fontSize: '1.5rem',
                    }}
                >
                    Static Blog by Gatsby JS
                </Link>
            </h1>
        </div>
    </div>
)

export default Header
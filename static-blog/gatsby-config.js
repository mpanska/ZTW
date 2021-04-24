module.exports = {
	siteMetadata: {
		title: 'Static Blog',
		description: `This is example of static Gatsby blog`,
		author: `@pwr`,
	},
	plugins: [
		'gatsby-plugin-react-helmet',
		`gatsby-plugin-image`,
		`gatsby-plugin-sharp`,
		`gatsby-transformer-sharp`,
		{
			resolve: `gatsby-source-filesystem`,
			options:{
				name: `src`,
				path: `${__dirname}/src/`
			}
		},
		{
			resolve: `gatsby-source-filesystem`,
			options: {
			  path: `${__dirname}/src/images`,
			},
		},
		{
			resolve: `gatsby-transformer-remark`,
			options: {
			  plugins: [
				{
				  resolve: `gatsby-remark-images`,
				  options: {
					maxWidth: 800,
				  },
				},
			  ],
			},
		  },
	],
}; 
